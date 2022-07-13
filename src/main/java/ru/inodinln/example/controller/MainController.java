package ru.inodinln.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inodinln.example.dto.DebitCardDTO;
import ru.inodinln.example.dto.LoanDTO;
import ru.inodinln.example.entity.User;
import ru.inodinln.example.service.DbtCrdService;
import ru.inodinln.example.service.LoanService;
import ru.inodinln.example.validation.entity.DbtCrdValidObj;
import ru.inodinln.example.validation.entity.LoanValidObj;
import ru.inodinln.example.validation.service.DbtCrdValidService;
import ru.inodinln.example.validation.service.LoanValidService;

@Controller
public class MainController {

   private User currentUser;
   private final DbtCrdValidService dbtCrdValidSrv;
   private final DbtCrdService dbtCrdService;
   private final LoanService loanService;
   private final LoanValidService loanValidService;

   public MainController(DbtCrdValidService dbtCrdValidSrv,
                         DbtCrdService dbtCrdService, LoanService loanService, LoanValidService loanValidService) {
       this.dbtCrdValidSrv = dbtCrdValidSrv;
       this.dbtCrdService = dbtCrdService;
       this.loanService = loanService;
       this.loanValidService = loanValidService;
   }


    @GetMapping("/index")
    public String index(Model model) {

        if (currentUser == null)
            return "redirect:/";

        String username = currentUser.getFirstName() +" " + currentUser.getLastName();
        model.addAttribute("username", username);
        return "index";
    }

//=======================================DEBIT CARDS SECTION=========================================
    @GetMapping("/cardsPage")
    public String cards(Model model) {

       if (currentUser == null)
           return "redirect:/";

       model.addAttribute("cards", dbtCrdService.getCardsToView(currentUser.getId()));
        return "cards/cards";
    }
    @GetMapping("/newCardPage")
    public String newCard(Model model) {

        if (currentUser == null)
            return "redirect:/";

       model.addAttribute("dbtCardDTO", new DebitCardDTO());
        return "cards/newCard";
    }

    @PostMapping("/addCard")
    public String addCard(@ModelAttribute("dbtCardDTO") DebitCardDTO dbtCardDTO, Model model) {

       if (currentUser == null)
            return "redirect:/";

        DbtCrdValidObj dbtCrdValidObj = dbtCrdValidSrv.dbtCrdValidation(dbtCardDTO);
        if (dbtCrdValidObj == null) {
            dbtCrdService.addCard(dbtCardDTO, currentUser);
            model.addAttribute("cards", dbtCrdService.getCardsToView(currentUser.getId()));
            return "cards/cards";
        }
        model.addAttribute("dbtCrdValidObj", dbtCrdValidObj);
        return "cards/newCard";
    }

    @GetMapping("/editCardPage/{cardNumber}")
    public String editCardPage (@PathVariable("cardNumber") int cardNumber, Model model) {

        if (currentUser == null)
            return "redirect:/";

        model.addAttribute("card", dbtCrdService.getCardToView(cardNumber));
       return "cards/editCard";
    }

    @PatchMapping("/updateCard/{cardNumber}") // попробовать PUT
    public String updateCard (@ModelAttribute("dbtCardDTO") DebitCardDTO dbtCardDTO,
                              @PathVariable("cardNumber") int cardNumber, Model model) {

        if (currentUser == null)
            return "redirect:/";

        DbtCrdValidObj dbtCrdValidObj = dbtCrdValidSrv.dbtCrdValidation(dbtCardDTO);
        if (dbtCrdValidObj == null) {
            dbtCrdService.updateCard(cardNumber, dbtCardDTO);
            model.addAttribute("cards", dbtCrdService.getCardsToView(currentUser.getId()));
            return "redirect:/cardsPage";
        }
        model.addAttribute("dbtCrdValidObj", dbtCrdValidObj);
        model.addAttribute("card", dbtCrdService.getCardToView(cardNumber));
        return "cards/editCard";
       }

    @DeleteMapping("/deleteCard/{cardNumber}")
    public String deleteCard (@PathVariable("cardNumber") int cardNumber, Model model) {

        if (currentUser == null)
            return "redirect:/";

       if (dbtCrdService.dbtCrdIsNull(cardNumber))
           return "redirect:/cardsPage";
       dbtCrdService.deleteCard(cardNumber);
        model.addAttribute("cards", dbtCrdService.getCardsToView(currentUser.getId()));
        return "redirect:/cardsPage";
    }

    //=======================================LOANS SECTION=========================================

    @GetMapping("/loansPage")
    public String loans(Model model) {

        if (currentUser == null)
            return "redirect:/";

      model.addAttribute("loans", loanService.getLoansToView(currentUser.getId()));
       return "loans/loans";
    }

    @GetMapping("/newLoanPage")
    public String newLoan(Model model) {

        if (currentUser == null)
            return "redirect:/";

        model.addAttribute("newLoan", new LoanDTO());
        return "loans/newLoan";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("newLoan") LoanDTO newLoan, Model model) {

        if (currentUser == null)
            return "redirect:/";

        LoanValidObj lvo = loanValidService.loanValidation(newLoan);
       if (lvo == null) {
           loanService.addLoan(newLoan, currentUser);
           model.addAttribute("loans", loanService.getLoansToView(currentUser.getId()));
           return "redirect:/loansPage";
       } else {
           model.addAttribute("loanValidObj", lvo);
           return "loans/newLoan";
       }
    }

    @GetMapping("/editLoanPage/{loanNumber}")
    public String editLoanPage (@PathVariable("loanNumber") int loanNumber, Model model) {

        if (currentUser == null)
            return "redirect:/";

        model.addAttribute("loan", loanService.getLoanToView(loanNumber));
        return "loans/editLoan";
    }

    @PatchMapping("/updateLoan/{loanNumber}")
    public String updateLoan (@ModelAttribute("loan") LoanDTO loan,
                              @PathVariable("loanNumber") int loanNumber, Model model) {

        if (currentUser == null)
            return "redirect:/";

        LoanValidObj lvo = loanValidService.loanValidation(loan);
        if (lvo == null) {
            loanService.updateLoan(loanNumber, loan);
        model.addAttribute("loans", loanService.getLoansToView(currentUser.getId()));
        return "redirect:/loansPage";
        }
        model.addAttribute("loanValidObj", lvo);
        model.addAttribute("loan", loanService.getLoanToView(loanNumber));
        return "loans/editLoan";
    }

    @DeleteMapping("/deleteLoan/{loanNumber}")
    public String deleteLoan (@PathVariable("loanNumber") int loanNumber, Model model) {

        if (currentUser == null)
            return "redirect:/";

        if (loanService.loanIsNull(loanNumber))
            return "redirect:/loansPage";
       loanService.deleteLoan(loanNumber);
        model.addAttribute("loans", loanService.getLoansToView(currentUser.getId()));
        return "redirect:/loansPage";
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
