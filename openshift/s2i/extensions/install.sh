#!/usr/bin/env bash
injected_dir=$1
source /usr/local/s2i/install-common.sh

S2I_CLI_SCRIPT="${injected_dir}/configuration.cli"

echo "/system-property=property1:add(value=property1-value)" > "${S2I_CLI_SCRIPT}"

run_cli_script "${S2I_CLI_SCRIPT}"


