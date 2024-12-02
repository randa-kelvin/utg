package com.untucapital.usuite.utg.model;//package com.untucapital.usuite.utg.model;
//
//import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.ClientRequestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "clientRequest")
//public class ClientRequestController {
//    @Autowired
//    private final ClientRequestService clientRequestService;
//
//    public ClientRequestController(ClientRequestService clientRequestService) {
//        this.clientRequestService = clientRequestService;
//    }
//    //Add
//    @PostMapping("/save")
//    public void save(@RequestBody ClientRequest clientRequest){
//        clientRequestService.save(clientRequest);
//    }
//    //Delete by id
//    @DeleteMapping("/delete/{clientRequestId}")
//    public void deleteById(@PathVariable("clientRequestId") String id){
//        clientRequestService.deleteById(id);
//    }
//    //Find all by loan id
//    @GetMapping("/get/{loanId}")
//    public List<ClientRequest> findAllByLoanId(@PathVariable("loanId") String id){
//        return clientRequestService.findAllByLoanId(id);
//    }
//}
