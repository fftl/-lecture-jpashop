package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    @NotEmpty //이걸 추가하면 필수값이 됩니다.
    private String name;

    @Embedded //내장타입!
    private Address address;

    @OneToMany(mappedBy = "member") //나는 읽기전용이야.
    private List<Order> orders = new ArrayList<>();

}
