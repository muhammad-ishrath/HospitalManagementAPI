
package com.example.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements
    ExceptionMapper<ResourceNotFoundException> {
        private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);
        
        @Override
        public Response toResponse(ResourceNotFoundException exception) {
        LOGGER.error("ResourceNotFoundException caught: {}",
        exception.getMessage(), exception);
            return Response.status(Response.Status.NOT_FOUND)
            .entity(exception.getMessage())
            .type(MediaType.TEXT_PLAIN)
            .build();
    }
}
