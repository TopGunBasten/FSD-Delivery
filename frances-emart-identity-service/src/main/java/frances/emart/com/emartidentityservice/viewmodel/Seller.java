package frances.emart.com.emartidentityservice.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Seller {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String companyName;

    @NotNull
    @NotEmpty
    private String GSTIN;

    @NotNull
    @NotEmpty
    private String brief;

    @NotNull
    @NotEmpty
    private String postalAddress;

    @NotNull
    @NotEmpty
    private String webSite;

    @NotNull
    @NotEmpty
    private String contractNumber;
}