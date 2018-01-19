#!/bin/bash
#
# @(#) シナリオのスタブを読み込むスクリプト。
#
#######################################################################################


# 本スクリプトのディレクトリのフルパス取得
CURRENT_SCRIPT_DIR=$(cd $(dirname $0); pwd)

# プロジェクトルートのフルパス取得
PROJECT_ROOT_DIR="${CURRENT_SCRIPT_DIR}/../../.."

# シナリオのスタブのフルパス取得
SCENARIO_STUB_DIR=${PROJECT_ROOT_DIR}/src/test/resources/scenario

# スタブの読み込み
cat ${SCENARIO_STUB_DIR}/$1/$1
