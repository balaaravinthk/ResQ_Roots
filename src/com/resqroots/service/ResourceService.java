package com.resqroots.service;

import com.resqroots.dao.ResourceDAO;
import com.resqroots.model.Resource;

public class ResourceService {

    private final ResourceDAO resourceDAO = new ResourceDAO();

    public boolean registerResource(Resource resource, String resourceType) {

        if (resource == null || resourceType == null || resourceType.isBlank()) {
            System.out.println("Invalid resource details.");
            return false;
        }

        return resourceDAO.addResource(resource, resourceType);
    }

    public boolean updateResourceStatus(int resourceId, String status) {
        return resourceDAO.updateResourceStatus(resourceId, status);
    }

    public void displayAllResources() {
        resourceDAO.displayResources();
    }
}