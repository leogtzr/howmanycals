#!/bin/bash
# set -x
#: Github: leogtzr
# note: this script is only for my personal usage, DO NOT USE IT IN PRODUCTION.

work_dir=$(dirname "$(readlink --canonicalize-existing "${0}" 2> /dev/null)")
readonly backup_dir="${work_dir}/backups"
readonly docker_compose_file="${work_dir}/docker-compose.yml"
readonly error_docker_file_not_found=80
readonly datetime=$(date '+%F-%H.%m.%S')

if [[ ! -f "${docker_compose_file}" ]]; then
    echo "docker-compose file not found" >&2
    exit ${error_docker_file_not_found}
fi

: ${HOWMANYCALS_DB_PASSWORD?} ${HOWMANYCALS_DB_USER?} ${HOWMANYCALS_DB?}

if [[ ! -d "${backup_dir}" ]]; then
    mkdir "${backup_dir}"
fi

if PGPASSWORD="${HOWMANYCALS_DB_PASSWORD}" docker-compose --file "${docker_compose_file}" \
    exec "${HOWMANYCALS_DB}" pg_dump --username leo "${HOWMANYCALS_DB}" > "${backup_dir}/db_dump-ALL-${datetime}.sql"; then
    printf "Full schema and data backup created at: %s\n" "${backup_dir}/db_dump-ALL-${datetime}.sql"
else
    printf "error: creating (schema and data) backup: %s\n" "${backup_dir}/db_dump-ALL-${datetime}.sql"
fi

# can be reloaded with: gunzip -c filename.gz | psql dbname
if PGPASSWORD="${HOWMANYCALS_DB_PASSWORD}" docker-compose --file "${docker_compose_file}" \
    exec "${HOWMANYCALS_DB}" pg_dump --data-only --username leo "${HOWMANYCALS_DB}" | gzip > "${backup_dir}/db_dump-DATA-${datetime}.sql.gz"; then
    printf "Data backup created at: %s\n" "${backup_dir}/db_dump-DATA-${datetime}.sql.gz"
else
    printf "error: creating (data) backup: %s\n" "${backup_dir}/db_dump-DATA-${datetime}.sql.gz"
fi

exit 0