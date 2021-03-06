#!/bin/bash
set -x
#: Github: leogtzr
# note: this script is only for my personal usage, DO NOT USE IT IN PRODUCTION.

work_dir=$(dirname "$(readlink --canonicalize-existing "${0}" 2> /dev/null)")
readonly docker_compose_file="${work_dir}/docker-compose.yml"
readonly export_schema_and_data_script="${work_dir}/export_schema_and_data.sh"
readonly error_docker_file_not_found=80

remove_db() {
    if [[ -f "${export_schema_and_data_script}" ]]; then
        "${export_schema_and_data_script}"
    fi
    # docker container rm $(docker container ls -f 'name=howmany*' -q) should work too...
    docker-compose --file "${docker_compose_file}" down
    docker-compose --file "${docker_compose_file}" stop
    docker-compose --file "${docker_compose_file}" kill

    echo "${MY_PASS}" | sudo -S rm --recursive --force "${work_dir}/database-data"
    
    docker-compose --file "${docker_compose_file}" up
    # if docker system prune --all --force; then
    #     docker-compose --file "${docker_compose_file}" up
    # fi
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

exit
