package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.CourseRequest;
import com.peaksoft.spring_rest_api_proect.dto.GroupRequest;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.entities.Group;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class GroupRequestConverter {

    public Group create(GroupRequest groupRequest) {
        if(groupRequest==null) {
            return null;
        }
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(Date.valueOf(LocalDate.now()));
        group.setImage(groupRequest.getImage());
        return  group;
    }

    public void update(Group group,GroupRequest groupRequest) {
        if (groupRequest.getGroupName() != null) {
            group.setGroupName(groupRequest.getGroupName());
        }
        if (groupRequest.getImage() != null) {
            group.setImage(groupRequest.getImage());
        }
    }

    }
