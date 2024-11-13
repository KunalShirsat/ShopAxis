package com.navbar.dto;

import lombok.Data;

@Data
public class CategoryDTO {
        private Long id;
        private String name;

        // Constructor with id and name parameters
        public CategoryDTO(Long id, String name) {
                this.id = id;
                this.name = name;
        }

        // Getters and Setters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}
