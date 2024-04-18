package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.service.InvenService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String invenListPage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){
        List<InvenDto> invenList = invenService.getAllInventoryItems();
        Pageable pageable = PageRequest.of(page,10);
        int maxPage = (int)Math.ceil((double) invenList.size()/10);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("invenList", invenList.subList(page * 8, Math.min((page + 1) * 8, invenList.size())));
        model.addAttribute("invenDto", new InvenDto());
        return "inven/invenList";
    }

    @PostMapping("/inven/process")
    public String processOperation(@ModelAttribute InvenDto invenDto, Model model) {
        if (invenDto != null && invenDto.getInvenStatus() != null) {
            InvenStatus invenStatus = invenDto.getInvenStatus();
            switch (invenStatus) {
                case BUY:
                    invenService.processBuy(invenDto);
                    break;
                case SELL:
                    if (invenDto.getId() != null && invenDto.getCount() > 0) { // id가 null이 아니고 수량이 0보다 큰지 확인
                        // 판매 처리 - 수량을 감소시킵니다.
                        invenService.processSell(invenDto.getId(), invenDto.getCount());
                    } else {
                        model.addAttribute("errorMsg","판매할 제품명과 수량을 확인해주세요.");
                    }
                    break;
                case MODIFY:
                    invenService.processModify(invenDto);
                    break;
                default:
                    // 처리할 수 없는 상태입니다. 오류 처리 로직 추가
                    break;
            }
        } else {
            // 유효하지 않은 입력입니다. 오류 처리 로직 추가
        }
        return "redirect:/inven/invenList";
    }
}