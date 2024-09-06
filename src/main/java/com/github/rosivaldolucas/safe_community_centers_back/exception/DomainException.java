package com.github.rosivaldolucas.safe_community_centers_back.exception;

public class DomainException extends RuntimeException {

  private final String message;

  public DomainException(final String message) {
    super();
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
