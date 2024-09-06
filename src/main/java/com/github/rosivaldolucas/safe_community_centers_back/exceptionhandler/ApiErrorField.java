package com.github.rosivaldolucas.safe_community_centers_back.exceptionhandler;

public record ApiErrorField(
        String field,
        String message
) {
}
