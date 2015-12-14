package net.bondar.web.service;

import net.bondar.web.dao.inter.PostDao;
import net.bondar.web.model.Contact;
import net.bondar.web.model.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by AzeraL on 14.12.2015.
 */
@Service
@Transactional
public class PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    SessionFactory sessionFactory;

    public Post savePost(Contact contactFrom, String content, LocalDateTime date) {
        Post post = new Post(contactFrom, content, date);
        return postDao.save(post);
    }

    public Post findPostById(long id) {
        return postDao.findById(id);
    }

    public Set<Post> findPosts(Contact contact) {
        return contact.getPosts();
    }
}