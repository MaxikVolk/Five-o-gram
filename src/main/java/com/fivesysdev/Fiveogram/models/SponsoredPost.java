package com.fivesysdev.Fiveogram.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sponsored_posts")
public class SponsoredPost extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "sponsor_id", referencedColumnName = "id")
    private User sponsor;

}
