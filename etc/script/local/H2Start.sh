#!/bin/bash
#
# @(#) ローカルでH2データベースを起動するスクリプト。
#
#######################################################################################


# 本スクリプトのディレクトリのフルパス取得
CURRENT_SCRIPT_DIR=$(cd $(dirname $0); pwd)

# プロジェクトルートのフルパス取得
PROJECT_ROOT_DIR="${CURRENT_SCRIPT_DIR}/../../.."

# ローカルライブラリのディレクトリへ移動
cd ${PROJECT_ROOT_DIR}/etc/library/local

# H2データベースの起動
java -cp h2-1.4.181.jar:h2storedprocedure-2.0.jar:../production/charset-2.0.jar org.h2.tools.Server