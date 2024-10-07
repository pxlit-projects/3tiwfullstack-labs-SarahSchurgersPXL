package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Notification {

    private Long id;
    private String from;
    private String to;
    private String subject;
    private String message;
}
