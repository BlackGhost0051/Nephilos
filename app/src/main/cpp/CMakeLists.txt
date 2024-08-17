cmake_minimum_required(VERSION 3.4.1)

project(arpspoof)

set(CMAKE_C_STANDARD 99)

add_executable(arpspoof arpspoof.c)
add_executable(fill_arp_table fill_arp_table.c)

set_target_properties(arpspoof PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
)

set_target_properties(fill_arp_table PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
)

add_custom_command(TARGET arpspoof POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy
        ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}/arpspoof
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libarpspoof.so
)

add_custom_command(TARGET fill_arp_table POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy
        ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}/fill_arp_table
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libfill_arp_table.so
)
