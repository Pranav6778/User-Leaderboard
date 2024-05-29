package com.crio.starter.data;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Users {
    @Id
    private int id;
    private String userName;
    private int score;
    private List <Badges> badges ;

    

}
