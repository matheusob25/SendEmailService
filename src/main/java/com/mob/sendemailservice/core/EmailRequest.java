package com.mob.sendemailservice.core;

public record EmailRequest(String from, String to, String subject, String body) {
}
