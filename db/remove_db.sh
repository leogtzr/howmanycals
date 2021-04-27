#!/bin/bash

work_dir=$(dirname "$(readlink --canonicalize-existing "${0}" 2> /dev/null)")
readonly docker_compose_file="${work_dir}/docker-compose.yml"
readonly error_docker_file_not_found=80

remove_db() {
    # docker container rm $(docker container ls -f 'name=howmany*' -q) should work too...
    docker-compose --file "${docker_compose_file}" down
    docker-compose --file "${docker_compose_file}" stop
    docker-compose --file "${docker_compose_file}" kill
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

if ask "Are you sure you want to remove the database?"; then
    if ask "Are you serious?"; then
        echo "OK, fine ... "
        remove_db #()
    fi
fi

exit 0