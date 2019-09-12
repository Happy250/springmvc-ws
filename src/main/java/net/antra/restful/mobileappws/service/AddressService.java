package net.antra.restful.mobileappws.service;

import net.antra.restful.mobileappws.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddresses(String id);

    AddressDto getAddress(String addressId);
}
