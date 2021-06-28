package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //(1)
    private static long sequence = 0L; //sequence: 0,1,2 키값 생성해준다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //store에 넣기 전에 member에 아이디 값을 세팅해주고,
        store.put(member.getId(), member); //store에 저장을 한다.
        return member; //세팅된 값에 따라 반환.
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store에서 get해서 id를 넣으면 된다. 결과가 없으면 optional로 감싼다(null반환가능성이 있으면)
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 멤버(values) 쫙 반환.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //store에서 루프로 돌린다. filter로 lambda 활용해서 member.getName()이 parameter로 넘어온 name과 같은지 확인한다.
                .findAny(); //찾으면 반환
    }

    public void clearStore() {
        store.clear();
    }
}
