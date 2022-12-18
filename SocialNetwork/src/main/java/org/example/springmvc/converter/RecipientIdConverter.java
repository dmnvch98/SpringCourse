package org.example.springmvc.converter;

import org.example.springmvc.model.User;

public interface RecipientIdConverter {
    User recipientIdToUser(final long recipientId);
}
