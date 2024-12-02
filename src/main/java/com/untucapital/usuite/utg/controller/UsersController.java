package com.untucapital.usuite.utg.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.untucapital.usuite.utg.dto.client.CustomerKyc;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.CmsUser;
import com.untucapital.usuite.utg.model.po.PoUser;
import com.untucapital.usuite.utg.model.tms.TmsUser;
import com.untucapital.usuite.utg.repository.ConfirmationTokenRepository;
import com.untucapital.usuite.utg.repository.RoleRepository;
import com.untucapital.usuite.utg.repository.UserRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.RandomNumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@RestController
@JsonSerialize
@JsonDeserialize
@RequestMapping(path = "users")
public class UsersController extends AbstractController<User> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public UsersController(UserService userService, ConfirmationTokenRepository confirmationTokenRepository) {
        this.userService = userService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    protected AbstractService<User> getService() {
        return userService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return new ResponseEntity<User>(userRepository.getUserById(id), HttpStatus.OK);
    }
    // Get list of all users with a certain Branch Name
    @GetMapping("/branch/{branchName}")
    public ResponseEntity<List<User>> getUsersByBranch(@PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<User>>(userRepository.findUsersByBranchOrderByCreatedAtDesc(branchName), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserByUserId(@PathVariable("id") String id) {
//        return new ResponseEntity<User>(userRepository.getUserById(id), HttpStatus.OK);
//    }

    @GetMapping ("getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        return new ResponseEntity<User>(userRepository.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping ("getUserKycById/{username}")
    public CustomerKyc getUserKycById(@PathVariable("username") String username) {
       return userService.getKyc(username);
    }

//    GET ALL Cash Management USERS
    @GetMapping("/cmsUser")
    public ResponseEntity<List<User>> getUsersByCmsUser() {
        return new ResponseEntity<List<User>>(userRepository.findUsersByCmsUser_RoleIsNotNullAndCmsUser_RoleNotLike(""), HttpStatus.OK);
    }

    @GetMapping("/poUser")
    public ResponseEntity<List<User>> getUsersByPoUser() {
        return new ResponseEntity<List<User>>(userRepository.findUsersByPoUser_RoleIsNotNullAndPoUser_RoleNotLike(""), HttpStatus.OK);
    }

    @GetMapping("/tmsUser")
    public ResponseEntity<List<User>> getUsersByTmsUser() {
        return new ResponseEntity<List<User>>(userRepository.findUsersByTmsUser_RoleIsNotNullAndTmsUser_RoleNotLike(""), HttpStatus.OK);
    }

    // Get list of all users with a certain Branch Name
    @GetMapping("/creditCommitGroup/{creditCommitGroupName}")
    public ResponseEntity<List<User>> getUsersByCreditCommitGroupName(@PathVariable("creditCommitGroupName") String creditCommitGroupName) {
        return new ResponseEntity<List<User>>(userRepository.findUsersByCreditCommitGroup(creditCommitGroupName), HttpStatus.OK);
    }

    // Get list of all users with role of Loan Officer
    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable("roleName") String roleName) {
        return new ResponseEntity<List<User>>(userService.getUsersByRole(roleName), HttpStatus.OK);
    }

    // Get list of all users with role of Loan Officer
    @GetMapping("/roleAndBranch/{roleName}/{branchName}")
    public ResponseEntity<List<User>> getUsersByRoleAndBranch(@PathVariable("roleName") String roleName, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<User>>(userService.getUsersByRoleAndBranch(roleName, branchName), HttpStatus.OK);
    }

    @GetMapping ("/getUserByMobileNumber/{mobileNumber}")
    public ResponseEntity<User> getUserByMobileNumber(@PathVariable("mobileNumber") Long mobileNumber) {
        return new ResponseEntity<User>(userRepository.getUserByContactDetail_MobileNumber(mobileNumber),HttpStatus.OK);
    }

    @GetMapping ("/getUserByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<User>(userRepository.getUserByUsername(username),HttpStatus.OK);
    }

    @GetMapping ("/getUserByEmailAddress/{email}")
    public ResponseEntity<User> getUserByEmailAddress(@PathVariable("email") String email) {
        return new ResponseEntity<User>(userRepository.getUserByContactDetail_EmailAddress(email),HttpStatus.OK);
    }

    @PutMapping("/updateUserRole/{id}")
        public ResponseEntity<String> updateUserRole(@PathVariable String id, @RequestBody User user) {
        User updatedUserRole = userRepository.getUserById(id);
        updatedUserRole.setRoles(user.getRoles());
        userRepository.save(updatedUserRole);
        return new ResponseEntity<String>("User role successfully updated", HttpStatus.OK);
    }
    @PutMapping("/updateCmsUserRole/{id}")
    public ResponseEntity<String> updateCmsUserRole(@PathVariable String id, @RequestBody CmsUser updatedCmsUser) {
        // Log the received ID for debugging
        System.out.println("Received ID: " + id);

        // Retrieve the User entity by ID
        Optional<User> optionalUser = userRepository.findUserById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Update the role of the existing user's CmsUser or create a new one if it's null
            CmsUser cmsUser = existingUser.getCmsUser();

            if (cmsUser == null) {
                cmsUser = new CmsUser();
            }

            cmsUser.setRole(updatedCmsUser.getRole());

            // Set the updated CmsUser back to the User entity
            existingUser.setCmsUser(cmsUser);

            // Save the updated user
            userRepository.save(existingUser);

            return new ResponseEntity<>("User role successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/userCleanup")
    public ResponseEntity<?> cleanupUser(@RequestParam String mobile, @RequestParam String activeAcc) {
        try {
            User updatedUser = userService.dbCleanup(mobile, activeAcc);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while cleaning up the user.");
        }
    }

    @PutMapping("/updatePoUserRole/{id}")
    public ResponseEntity<String> updatePoUserRole(@PathVariable String id, @RequestBody PoUser updatedPoUser) {
        // Log the received ID for debugging
        System.out.println("Received ID: " + id);

        // Retrieve the User entity by ID
        Optional<User> optionalUser = userRepository.findUserById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Update the role of the existing user's CmsUser or create a new one if it's null
            PoUser poUser = existingUser.getPoUser();

            if (poUser == null) {
                poUser = new PoUser();
            }

            poUser.setRole(updatedPoUser.getRole());

            // Set the updated CmsUser back to the User entity
            existingUser.setPoUser(poUser);

            // Save the updated user
            userRepository.save(existingUser);

            return new ResponseEntity<>("User role successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTmsUserRole/{id}")
    public ResponseEntity<String> updateTmsUserRole(@PathVariable String id, @RequestBody TmsUser updatedTmsUser) {
        // Log the received ID for debugging
        System.out.println("Received ID: " + id);

        // Retrieve the User entity by ID
        Optional<User> optionalUser = userRepository.findUserById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Update the role of the existing user's CmsUser or create a new one if it's null
            TmsUser tmsUser = existingUser.getTmsUser();

            if (tmsUser == null) {
                tmsUser = new TmsUser();
            }

            tmsUser.setRole(updatedTmsUser.getRole());

            // Set the updated CmsUser back to the User entity
            existingUser.setTmsUser(tmsUser);

            // Save the updated user
            userRepository.save(existingUser);

            return new ResponseEntity<>("User role successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user){
        User updatedUser = userRepository.getUserById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());

        updatedUser.setUsername(user.getUsername());
        updatedUser.setContactDetail(user.getContactDetail());
        updatedUser.setDirtOfBirth(user.getDirtOfBirth());
        updatedUser.setMaritalStatus(user.getMaritalStatus());
        updatedUser.setGender(user.getGender());
        updatedUser.setCity(user.getCity());
        updatedUser.setNationalId(user.getNationalId());
        updatedUser.setSuburb(user.getSuburb());
        updatedUser.setStreetName(user.getStreetName());
        updatedUser.setStreetNumber(user.getStreetNumber());

        userRepository.save(updatedUser);
        return new ResponseEntity<String>("User Info Status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/updateKycInfo/{id}")
    public ResponseEntity<String> updateKycInfo(@PathVariable String id, @RequestBody User user){
        User updatedUser = userRepository.getUserById(id);

        updatedUser.setNationalId(user.getNationalId());
        updatedUser.setMaritalStatus(user.getMaritalStatus());
        updatedUser.setCity(user.getCity());
        updatedUser.setSuburb(user.getSuburb());
        updatedUser.setStreetName(user.getStreetName());
        updatedUser.setStreetNumber(user.getStreetNumber());

        userRepository.save(updatedUser);
        return new ResponseEntity<String>("User Info Status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/updateUserPassword/{id}")
    public ResponseEntity<String> updateUserPassword(@PathVariable String id, @RequestBody User user){
        User updatedUser = userRepository.getUserById(id);
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
        return new ResponseEntity<String>("User password Info Status successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        String message = userService.deleteUserById(id);
        return  new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PutMapping("/updateUserBranch/{id}")
    public ResponseEntity<String> updateUserBranch(@PathVariable String id, @RequestBody User user){
        User updatedUserBranch = userRepository.getUserById(id);
        updatedUserBranch.setBranch(user.getBranch());
        userRepository.save(updatedUserBranch);
        return  new ResponseEntity<String>("User branch successfully updated", HttpStatus.OK);
    }
    @PutMapping("/updateUserGroup/{id}")
    public ResponseEntity<String> updateUserGroup(@PathVariable String id, @RequestBody User user){
        User updatedUserGroup = userRepository.getUserById(id);
        updatedUserGroup.setCreditCommitGroup(user.getCreditCommitGroup());
        userRepository.save(updatedUserGroup);
        return  new ResponseEntity<String>("User group successfully updated", HttpStatus.OK);
    }

    // Get list of all users with a certain Branch Name
    @GetMapping("/untuStaff")
    public ResponseEntity<List<User>> getUntuStaff() {
        return new ResponseEntity<List<User>>(userRepository.findUsersByBranchNotNullOrderByCreatedAtDesc(), HttpStatus.OK);
    }

    @GetMapping("/getUsersByCmsUserRole/{role}")
    public ResponseEntity<List<User>> getUsersByCmsUserRole(@PathVariable("role") String role) {
        try {
            List<User> users = userRepository.findUsersByCmsUser_Role(role);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving users by CMS user role: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getUsersByTmsUserRole/{role}")
    public ResponseEntity<List<User>> getUsersByTmsUserRole(@PathVariable("role") String role) {
        try {
            List<User> users = userRepository.findUsersByTmsUser_Role(role);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving users by CMS user role: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    change expired token
    @PutMapping("/updateExpiredToken/{mobile}")
    public ResponseEntity<String> updateExpiredToken(@PathVariable long mobile, @RequestBody User user){
        User updateExpiredToken = userRepository.getUserByContactDetail_MobileNumber(mobile);

        if (updateExpiredToken != null) {
            // Generate and Save confirmation token
            String token = RandomNumUtils.generateCode(6);
//            ConfirmationToken confirmToken = new ConfirmationToken();
//            confirmToken.setToken(token);
//            confirmToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));
//            confirmToken.setUser(updateExpiredToken);
//            confirmationTokenRepository.save(confirmToken);

            updateExpiredToken.setResetPasswordToken(token);
            userRepository.save(updateExpiredToken);
            return  new ResponseEntity<String>("User token successfully updated", HttpStatus.OK);

        } else {
            throw new ResourceNotFoundException("Could not find any %s with the mobile number: ", "user" ,mobile);
        }

    }

    @PutMapping("/addMusoniClientId/{userId}")
    public ResponseEntity<String> updateUserMusoniClientId(@PathVariable String userId, @RequestBody User user){
        User updatedUser = userRepository.getUserById(userId);
        updatedUser.setMusoniClientId(user.getMusoniClientId());
        userRepository.save(updatedUser);
        return new ResponseEntity<String>("Musoni Client synced Id successfully updated.", HttpStatus.OK);
    }

//    public ResponseEntity<String> updateCcFinalMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
//        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
//        updatedLoanStatus.setCcDate(clientLoan.getCcDate());
//        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
//        clientRepository.save(updatedLoanStatus);
//        return new ResponseEntity<String>("Meeting status successfully updated.", HttpStatus.OK);
//    }



}
