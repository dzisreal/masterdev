#cd $(dirname $0)
SRC_DIR=.
DST_DIR=../src/main/java
protoc  --java_out=$DST_DIR $SRC_DIR/DataTrackingProto.proto