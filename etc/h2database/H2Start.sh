#!/bin/bash
#
# @(#) ローカルでH2データベースを起動するスクリプト。
#
#######################################################################################


# 本スクリプトのディレクトリのフルパス取得
H2_HOME=$(cd $(dirname $0); pwd)

# ライブラリ格納先ディレクトリ
H2_LIBRARY_PATH=${H2_HOME}/lib

# H2Databaseの起動オプション。詳細は、-help で
H2_OPT=-tcp
#H2_OPT=-tcp -web

# クラスライブラリの指定
CLASSPATH=$CLASSPAHT:${H2_LIBRARY_PATH}/h2-1.4.181.jar:${H2_LIBRARY_PATH}/h2storedprocedure-2.0.jar:${H2_LIBRARY_PATH}/charset-2.0.jar

# ローカルライブラリのディレクトリへ移動
cd $H2_HOME

# H2データベースの起動
exec java -cp $CLASSPATH org.h2.tools.Server $H2_OPT &
