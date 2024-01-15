package com.example.beginspringboot.service.posts;

import com.example.beginspringboot.domain.posts.Posts;
import com.example.beginspringboot.domain.posts.PostsRepository;
import com.example.beginspringboot.web.dto.PostsListResponseDto;
import com.example.beginspringboot.web.dto.PostsResponseDto;
import com.example.beginspringboot.web.dto.PostsSaveRequestDto;
import com.example.beginspringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //의존성 주입 받을 때, @Autowired도 있지만 생성자로 주입받는 방식이 권장된다. lombok의 @RequiredArgsConstructor가 생성자를 만들어주고 의존성을 주입받는다.
    // 의존성 관계가 변경되어도 생성자를 변경하지 않고 필드값만 조정해주면 수정되잖아.
    @Transactional//작업을 처리하던 중 오류가 발생하면 모든 작업들을 원상태로 되돌림
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {//수정하는게 없으니 @Transactional이 없는게 아닐까
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);//넘겨줄 때, entity 자체를 넘기지말고 Dto로 변환해서 넘기자
    }

    @Transactional(readOnly = true)//이 옵션을 주는 이유가 있는데 이유는 이해 못했음
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }//postsRepository.findAllDesc()으로 객체 집합을 만들어 놓고
    //stream()으로 변환
    //map(각 객체가 적용되어야 하는 변화, 꼴)
    //collect(Collectors.toList())로 stream 객체를 list로 뱐환( collect: 모은다

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
