package net.bondar.web.service;

import net.bondar.web.dao.inter.PostDao;
import net.bondar.web.exceptions.NoSuchObjectException;
import net.bondar.web.model.Contact;
import net.bondar.web.model.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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

    public Post savePost(Contact contactFrom, String content, Date date) {
        Post post = new Post(contactFrom, content, date);
        return postDao.save(post);
    }

    public Post savePost(Post post){
        Post savedPost = postDao.save(post);
        postDao.flush();
        return savedPost;
    }

    public Post updatePost(Post post){
        Post updatedPost = postDao.update(post);
        postDao.flush();
        return  updatedPost;

    }

    public void deletePost(Post post){
        postDao.delete(post);
        postDao.flush();
    }

    public void deletePost(long id){
        postDao.delete(id);
        postDao.flush();
    }

    public Post findPostById(long id) {
        Post result = postDao.findById(id);
        if(result==null) throw new NoSuchObjectException();
        return result;
    }

    public List<Post> findPosts(Contact contact) {
        return contact.getPosts();
    }
}
