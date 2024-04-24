package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.*;
import com.example.HMS_MANAGEMENT.entity.CommuteListEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.repository.DesignerRepo;
import com.example.HMS_MANAGEMENT.service.CommuteListService;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import com.example.HMS_MANAGEMENT.service.DesignerService;
import com.example.HMS_MANAGEMENT.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DesignerController {

    private final DesignerService designerService;
    private final EmailService emailService;

    private final CommuteListService commuteListService;

    @Autowired
    public DesignerController(DesignerService designerService, EmailService emailService, CommuteListService commuteListService){
        this.designerService = designerService;
        this.emailService = emailService;
        this.commuteListService = commuteListService;
    }

    @GetMapping("/designer")
    public String designerMain(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){

        List<DesignerDto> designerDtoList = designerService.getAllDesigners();
        Pageable pageable = PageRequest.of(page, 8);
        List<DesignerDto> designerDtoListWithDayOff = designerService.getAllDesignersWithDayOff();
        int maxPage = (int)Math.ceil((double) designerDtoListWithDayOff.size()/8);
        model.addAttribute("designerDtoList", designerDtoListWithDayOff.subList(page * 8, Math.min((page + 1) * 8, designerDtoListWithDayOff.size())));
        model.addAttribute("designerDto", new DesignerDto());
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);


        return "designer/designerMain";
    }

    @GetMapping("/designer/scheduleList")
    public String scheduleList(){

        return "designer/scheduleList";
    }

    @GetMapping("/designer/salaryList")
    public String salaryList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){
        List<DesignerDto> designerDtoList = designerService.getAllDesigners();
        Pageable pageable = PageRequest.of(page, 8);
        int maxPage = (int)Math.ceil((double) designerDtoList.size()/8);
        model.addAttribute("designerDtoList", designerDtoList.subList(page * 8, Math.min((page + 1) * 8, designerDtoList.size())));
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);

        return "designer/salaryList";
    }

    @GetMapping("/designer/commuteList")
    public String commuteList(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){

        List<CommuteListDto> commuteListDto = commuteListService.getCommuteListByDate(date);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("commute", commuteListDto);

        return "designer/commuteList";
    }

    @GetMapping("/designer/detailPage/{id}")
    public String detailPage(@PathVariable Long id, Model model){

        DesignerDto designerDto = designerService.getDesignerByID(id);

        model.addAttribute("designerDto",designerDto);

        return "designer/detailPage";
    }

    @GetMapping("/designer/regPage")
    public String regPage(Model model){

        model.addAttribute("designerDto", new DesignerDto());

        return "designer/designerReg";
    }

    @GetMapping("/designer/salaryPage/{id}")
    public String salaryPage(@PathVariable Long id, Model model){

        DesignerDto dto = designerService.getDesignerByID(id);

        model.addAttribute("designer",dto);

        return "designer/salaryPage";
    }

    @PostMapping("/designer/reg")
    public String designerChk(@ModelAttribute("designerDto") @Valid DesignerDto designerDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMsg",designerDto);
            return "designer/designerReg";
        }
        List<String> selectedDayOffList = designerDto.getDayOffList().stream()
                .filter(dayOff -> !dayOff.isEmpty())
                .collect(Collectors.toList());
        designerDto.setDayOffList(selectedDayOffList);

        designerDto.setSalDate(designerDto.getSalDate());
        designerService.designerSave(designerDto);

        return "redirect:/designer";
    }

    @PostMapping("/designer/salaryPage/{id}")
    public ResponseEntity<String> sendSalaryEmail(@RequestBody EmailDto emailDto, Long id) {
        DesignerDto designerDto = designerService.getDesignerByID(id);
        try {
            // 이메일 내용 구성
            StringJoiner emailContent = new StringJoiner("\n");
            emailContent.add("디자이너: " + emailDto.getDesignerName());
            emailContent.add("기본급: " + emailDto.getBasicSal());
            emailContent.add("초과근무수당: " + emailDto.getOvertimeAllowance());
            emailContent.add("식비: " + emailDto.getMealAllowance());
            emailContent.add("국민연금: " + emailDto.getPension());
            emailContent.add("건강보험: " + emailDto.getHealthInsurance());
            emailContent.add("고용보험: " + emailDto.getEmploymentInsurance());
            emailContent.add("근로소득세: " + emailDto.getIncomeTax());
            emailContent.add("실 수령액: " + emailDto.getNetSalary());

            // EmailDto에 이메일 내용 설정
            emailDto.setEmailContent(emailContent.toString());

            // 이메일 전송
            emailService.sendSalaryEmail(emailDto);
            return ResponseEntity.ok("급여명세서 이메일이 성공적으로 전송되었습니다.");

        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("급여명세서 이메일 전송 중 오류가 발생했습니다.");
        }
    }

}
