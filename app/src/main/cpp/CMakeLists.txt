cmake_minimum_required(VERSION 3.4.1)

project(arpspoof)

set(CMAKE_C_STANDARD 99)

add_executable(arpspoof arpspoof.c)

set_target_properties(arpspoof PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
)


add_custom_command(TARGET arpspoof POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy
        ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}/arpspoof
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libarpspoof.so
)
