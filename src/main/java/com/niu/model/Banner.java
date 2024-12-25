package com.niu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private long id;
    @Column(length = 16)
    private String name;
    @Transient //不会生成该字段
    private String description;
    private String img;
    private String title;

    //假设有多个banneritem 表面一对多则使用@OneToMany,为什么他会生成第三张表，因为没有指定外键
    //外键字段需要指定@OneToMany @JoinColumn(name="bannerId")在1:N的1上指定，@JoinColumn指定在关系的维护方
    //@OneToMany @JoinColumn(name="bannerId")
    //private List<Banneritem> items;
    // @OneToMany(fetch = FetchType.EAGER) 一条sql查出来数据，Lazy则是懒加载
    //如果查询item的时候需要返回1的数据则使用@ManyToOne(mapperBy="banner",fetch=FetchType.EAGER)，达成一个双向的关系，在N方指定
}
