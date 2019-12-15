package mum.itweet.service.impl;

import mum.itweet.model.Post;
import mum.itweet.model.User;
import mum.itweet.model.dto.PostDto;
import mum.itweet.model.lookups.PostStatus;
import mum.itweet.repository.PostRepository;
import mum.itweet.repository.UserRepository;
import mum.itweet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post create(PostDto postDto) {

        PostStatus postStatus=PostStatus.Active;
        User user=userRepository.getOne(postDto.getUserId());
        Post post =new Post(user,postDto.getPostText(),postStatus,postDto.getImageUrl(),postDto.getVideoUrl(),new Date(),new Date());
        return postRepository.save(post);
    }

    @Override
    public Post get(long id) {

        return postRepository.findById(id).get();
    }

    @Override
    public Post update(PostDto postDto) {

        return create(postDto);
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }



    @Override
    public  List<Post> findByUserId(int userId) {

        return postRepository.findByUserId(userId);
    }

    @Override
    public int getLikesCount(long postId) {

        return postRepository.getLikesCount(postId);
    }

    @Override
    public int getCommentsCount(long postId) {

        return postRepository.getCommentsCount(postId);
    }



    @Override
    public  List<Post> listPendingPosts() {
        return  postRepository.findByStatusOrderByIdDesc(PostStatus.Pending);
    }

    @Override
    public  List<Post> listPostForUser(int userId) {
        return postRepository.listPostForUser(userId);
    }

    @Override
    public Post updateStatus(long postId, PostStatus postStatus){
        Post post =get(postId);
        post.setStatus(postStatus);
        return postRepository.save(post);
    }

}
