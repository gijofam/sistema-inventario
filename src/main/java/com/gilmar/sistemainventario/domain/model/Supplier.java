
package com.gilmar.sistemainventario.domain.model;

public class Supplier {
    private Long id;
    private String name;
    private String contactName;
    private String email;
    private String phone;
    private String address;

    public Supplier(Long id, String name, String contactName, String email, String phone, String address) {
        validateRequired(name,"El nombre de la empresa proveedora es obligatorio.");
        validateRequired(contactName, "El nombre del contacto es obligatorio.");
        validateEmail(email);   
        validateRequired(phone, "El teléfono es obligatorio.");
        this.id = id;
        this.name =  name;
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public void updateDetails(String name, String phone, String email) {
        validateRequired(name,"El nombre de la empresa proveedora es obligatorio.");
        validateRequired(contactName, "El nombre del contacto es obligatorio.");
        validateEmail(email);
        validateRequired(phone, "El teléfono es obligatorio.");
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

        // --- MÉTODOS DE VALIDACIÓN DE DOMINIO ---
    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El correo del proveedor es obligatorio.");
        }
        // Validación de negocio: debe contener al menos un arroba y un punto
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
        }
    }

    // private void validatePhone(String phone) {
    //     if (phone == null || phone.isBlank()) {
    //         throw new IllegalArgumentException("El teléfono de contacto es obligatorio.");
    //     }
    // }

    //     private void validateName(String name) {
    //     if (name == null || name.isBlank()) {
    //         throw new IllegalArgumentException("El nombre del proveedor es obligatorio.");
    //     }
    // }

     private void validateRequired(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

}