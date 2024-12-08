cmake_minimum_required(VERSION 3.4.1)

project(arpspoof)

set(CMAKE_C_STANDARD 99)

add_executable(arp_spoof arp_spoof.c)
add_executable(fill_arp_table fill_arp_table.c)
add_executable(get_interfaces get_interfaces.c)

set_target_properties(arp_spoof PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
)
set_target_properties(fill_arp_table PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
)
set_target_properties(get_interfaces PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}
)


add_custom_command(TARGET arp_spoof POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy
        ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}/arp_spoof
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libarp_spoof.so
)
add_custom_command(TARGET fill_arp_table POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy
        ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}/fill_arp_table
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libfill_arp_table.so
)
add_custom_command(TARGET get_interfaces POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy
        ${CMAKE_BINARY_DIR}/libs/${ANDROID_ABI}/get_interfaces
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libget_interfaces.so
)