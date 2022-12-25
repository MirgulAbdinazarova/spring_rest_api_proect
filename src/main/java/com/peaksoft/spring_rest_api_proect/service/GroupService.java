package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.CourseResponse;
import com.peaksoft.spring_rest_api_proect.dto.GroupRequest;
import com.peaksoft.spring_rest_api_proect.dto.GroupResponse;
import com.peaksoft.spring_rest_api_proect.entities.Group;

import java.util.List;

public interface GroupService {

     GroupResponse saveGroup(Long courseId,GroupRequest groupRequest);

    GroupResponse deleteGroupById(Long groupId);

    GroupResponse getGroupById(Long groupId);

    GroupResponse updateGroupById(Long groupId,GroupRequest groupRequest);

    List<GroupResponse> findAllGroups();

    void assignGroupToCourse(Long groupId, Long courseId);
}
