package com.pluralsight.model;

/**
 * Task importance. Enum order matters for sorting:
 * LOW < MEDIUM < HIGH < CRITICAL
 * (So reversed order puts CRITICAL first.)
 */
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}

