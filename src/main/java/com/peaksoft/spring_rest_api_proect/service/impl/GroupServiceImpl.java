package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.GroupRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.GroupResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.GroupRequest;
import com.peaksoft.spring_rest_api_proect.dto.GroupResponse;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.entities.Group;
import com.peaksoft.spring_rest_api_proect.repo.CourseRepository;
import com.peaksoft.spring_rest_api_proect.repo.GroupRepository;
import com.peaksoft.spring_rest_api_proect.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private  final CourseRepository courseRepository;

    private final GroupRequestConverter groupRequestConverter;

    private  final GroupResponseConverter groupResponseConverter;

    @Override
    public GroupResponse saveGroup(Long courseId,GroupRequest groupRequest) {
      Course course = courseRepository.findById(courseId).get();
      Group group = groupRequestConverter.create(groupRequest);
      course.addGroup(group);
      group .addCourse(course);
      groupRepository.save(group);
      return groupResponseConverter.viewGroup(group);
    }

    @Override
    public GroupResponse deleteGroupById(Long groupId) {
       Group group = groupRepository.findById(groupId).get();
       groupRepository.delete(group);
       return groupResponseConverter.viewGroup(group);
    }

    @Override
    public GroupResponse getGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return new GroupResponse(group.getId(),group.getGroupName(),group.getDateOfStart(),group.getImage());
    }

    @Override
    public GroupResponse updateGroupById(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).get();
        groupRequestConverter.update(group, groupRequest);
        return groupResponseConverter.viewGroup(groupRepository.save(group));
    }

    @Override
    public List<GroupResponse> findAllGroups() {
        return groupResponseConverter.view(groupRepository.findAll());
    }

    @Override
    public void assignGroupToCourse(Long groupId, Long courseId) {
        if (groupId != null) {
            Group group = groupRepository.findById(groupId).get();
            if (courseId != null) {
                Course course = courseRepository.findById(courseId).get();
                group.addCourse(course);
                course.addGroup(group);
                courseRepository.save(course);
            }else {
                System.out.println("course id is null");
            }
        }else {
            System.out.println("group id is null");
        }

    }

}
