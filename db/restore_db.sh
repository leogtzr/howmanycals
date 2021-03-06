#!/bin/bash
set -x
#: Github: leogtzr
# note: this script is only for my personal usage, DO NOT USE IT IN PRODUCTION.

work_dir=$(dirname "$(readlink --canonicalize-existing "${0}" 2> /dev/null)")
readonly docker_compose_file="${work_dir}/docker-compose.yml"
readonly error_docker_file_not_found=80

remove_db() {
    # docker container rm $(docker container ls -f 'name=howmany*' -q) should work too...
    docker-compose --file "${docker_compose_file}" down
    docker-compose --file "${docker_compose_file}" stop
    docker-compose --file "${docker_compose_file}" kill

    echo "${MY_PASS}" | sudo -S rm --recursive --force "${work_dir}/database-data"
    docker system prune --all --force

    if docker system prune --all --force; then
        if docker-compose --file "${docker_compose_file}" up --detach; then
            # docker-compose --file "${docker_compose_file}" ps --all | \
            #     grep --extended-regexp '^howmanycals.*'
            local service_id_output=$(docker-compose --file "${docker_compose_file}" ps --quiet 'howmanycals')
            if [[ -z "${service_id_output}" ]]; then
                echo "Service is NOT running 😟"
            else
                echo "Service is running ... "
                notify-send "We are up... 😊"
            fi
        fi

    fi
}

ask() {
    local ans 
    echo -n "${@}" '[y/n] ';
    read ans;
    case "${ans}" in 
        y*|Y*)
            return 0
        ;;
        *)
            return 1
        ;;
    esac
}

if [[ ! -f "${docker_compose_file}" ]]; then
    echo "docker-compose file not found" >&2
    exit ${error_docker_file_not_found}
fi

remove_db #()

# if ask "Are you sure you want to remove the database?"; then
#     if ask "Are you serious?"; then
#         echo "OK, fine ... "
#         remove_db #()
#     fi
# fi

exit 0
