#!/bin/bash

work_dir=$(dirname "$(readlink --canonicalize-existing "${0}" 2> /dev/null)")
readonly docker_compose_file="${work_dir}/docker-compose.yml"
readonly error_docker_file_not_found=80

if [[ ! -f "${docker_compose_file}" ]]; then
    echo "docker-compose file not found" >&2
    exit ${error_docker_file_not_found}
fi

: ${HOWMANYCALS_DB_PASSWORD?} ${HOWMANYCALS_DB_USER?} ${HOWMANYCALS_DB?}

PGPASSWORD=${HOWMANYCALS_DB_PASSWORD} docker-compose --file "${docker_compose_file}" \
    exec ${HOWMANYCALS_DB} psql --host=${HOWMANYCALS_DB} -U leo ${HOWMANYCALS_DB}

exit 0