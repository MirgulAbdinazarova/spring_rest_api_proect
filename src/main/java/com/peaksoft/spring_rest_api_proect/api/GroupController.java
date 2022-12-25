package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.GroupRequest;
import com.peaksoft.spring_rest_api_proect.dto.GroupResponse;
import com.peaksoft.spring_rest_api_proect.entities.Group;
import com.peaksoft.spring_rest_api_proect.service.GroupService;
import com.peaksoft.spring_rest_api_proect.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;
    private final StudentService studentService;
    @PostMapping("/{courseId}")
    public GroupResponse saveGroup(@PathVariable Long courseId,@RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(courseId,groupRequest);
    }
    @GetMapping("/{groupId}")
    public GroupResponse getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

    @GetMapping("/all")
    public List<GroupResponse> getAllGroups(){
        return groupService.findAllGroups();
    }
//    @DeleteMapping("/groupId")
//    public ResponseEntity deleteGroupById(@PathVariable Long groupId) {
//        return ResponseEntity.status(HttpStatus.OK).body("User with id:"+groupId+"deleted");
//    }

     @DeleteMapping("/{groupId}")
     public GroupResponse deleteGroup(@PathVariable Long groupId) {
        return groupService.deleteGroupById(groupId);
     }

    @PutMapping("/{groupId}")
    public GroupResponse updateGroup(@PathVariable Long groupId,
                                     @RequestBody GroupRequest groupRequest) {
     return  groupService.updateGroupById(groupId,groupRequest);
    }

    @PostMapping("/{studentId}/assign/{groupId}")
    public GroupResponse assignStudentToGroup(@PathVariable Long studentId,
                                              @PathVariable Long groupId) {
        studentService.assignStudentToGroup(studentId,groupId);
        return  groupService.getGroupById(groupId);
    }


}
