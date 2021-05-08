#!/usr/bin/env bash
injected_dir=$1
source /usr/local/s2i/install-common.sh

S2I_CLI_SCRIPT="${injected_dir}/configuration.cli"

run_cli_script "${S2I_CLI_SCRIPT}"
