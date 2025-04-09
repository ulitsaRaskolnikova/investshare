package ulitsa.raskolnikova.investshare.entity.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "physical_face_project_account")
@Entity
public class PhysicalFaceProjectAccountEntity extends ProjectAccountEntity {
    @Column(name = "passport_series")
    private Integer passportSeries;

    @Column(name = "passport_number")
    private Integer passportNumber;

    @Column(name = "passport_givenby", length = Integer.MAX_VALUE)
    private String passportGivenBy;

    @Column(name = "registration_address", length = Integer.MAX_VALUE)
    private String registrationAddress;

    @Column(name = "passport_page_with_photo")
    private byte[] passportPageWithPhoto;

    @Column(name = "passport_page_with_propiska")
    private byte[] passportPageWithPropiska;

    @Column(name = "svid_o_postanovke_na_uchet_phys_litsa")
    private byte[] svidOPostanovkeNaUchetPhysLitsa;
}