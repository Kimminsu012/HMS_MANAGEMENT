package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.service.InvenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvenController {

    private final InvenService invenService;

    public InvenController(InvenService invenService) {
        this.invenService = invenService;
    }

    @GetMapping("/inven")
    public String invenMain(Model model){
        return "inven/invenMain";
    }

    @GetMapping("/inven/invenList")
    public String invenListPage(InvenDto invenDto, Model model){

        model.addAttribute("invenList",invenDto);

        return "inven/invenList";
    }

    // 운영 유형에 따라 다른 동작 수행
    @PostMapping("/inven/process")
    public String processOperation(@ModelAttribute InvenDto invenDto, Model model) {
        String invenStatus = invenDto.invenStatus(); // DTO에서 운영 유형 가져오기

        if (invenStatus.equals(InvenStatus.BUY.toString())) {
            // 구매 처리
            invenService.invenSave(invenDto);
            model.addAttribute("invenDto", new InvenDto());

            invenService.processBuy(invenDto);
        } else if (invenStatus.equals(InvenStatus.SELL.toString())) {
            // 판매 처리
            invenService.processSell(invenDto);
        } else if (invenStatus.equals(InvenStatus.MODIFY.toString())) {
            // 수정 처리
            invenService.processModify(invenDto);
        }

        // 처리 후 리다이렉트 또는 다른 작업 수행
        return "redirect:/inven/invenList";
    }
}
