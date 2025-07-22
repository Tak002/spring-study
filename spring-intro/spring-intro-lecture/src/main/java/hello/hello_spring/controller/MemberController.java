package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {

    public final MemberService memberService;

    // spring  container에 보관하고 있던 bean객체를 자동으로 찾아서 주입
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String newMember(){
        return "members/createMembersForm";
    }


    @GetMapping("/members")
    public String members(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm){
        Member member=new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }
}
