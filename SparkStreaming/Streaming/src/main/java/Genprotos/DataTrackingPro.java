// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DataTrackingProto.proto

package Genprotos;

public final class DataTrackingPro {
  private DataTrackingPro() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DataTracking_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DataTracking_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027DataTrackingProto.proto\"l\n\014DataTrackin" +
      "g\022\017\n\007version\030\001 \002(\t\022\014\n\004name\030\002 \002(\t\022\021\n\ttime" +
      "stamp\030\003 \002(\006\022\020\n\010phone_id\030\004 \001(\t\022\013\n\003lon\030\005 \001" +
      "(\006\022\013\n\003lat\030\006 \001(\006B\036\n\tGenprotosB\017DataTracki" +
      "ngProP\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_DataTracking_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_DataTracking_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DataTracking_descriptor,
        new java.lang.String[] { "Version", "Name", "Timestamp", "PhoneId", "Lon", "Lat", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
