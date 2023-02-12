package com.egeuniversity.Tez.Service.Address;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import com.egeuniversity.Tez.Repository.Address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;

    @Override
    public Address addAddress(AddressRequestDTO addressRequestDTO) {
        return addressRepository.save(assembleAddress(addressRequestDTO));
    }

    @Override
    public Address getAddress(Integer id) {
        return addressRepository.getById(id);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address assembleAddress(AddressRequestDTO addressRequestDTO) {
        return Address.builder()
                .addressName(addressRequestDTO.getAddressName())
                .city(addressRequestDTO.getCity())
                .district(addressRequestDTO.getDistrict())
                .neighborhood(addressRequestDTO.getNeighborhood())
                .addressDesc(addressRequestDTO.getAddressDesc())
                .build();
    }

    @Override
    public Address updateAddress(Integer id, AddressRequestDTO addressDTO) {
        Address address = this.getAddress(id);
        if(address != null){
            if(addressDTO.getAddressName() != null || !addressDTO.getAddressName().equals("")) address.setAddressName(addressDTO.getAddressName());
            if(addressDTO.getCity() != null || !addressDTO.getCity().equals("")) address.setCity(addressDTO.getCity());
            if(addressDTO.getDistrict() != null || !addressDTO.getDistrict().equals("")) address.setDistrict(addressDTO.getDistrict());
            if(addressDTO.getNeighborhood() != null || !addressDTO.getNeighborhood().equals("")) address.setNeighborhood(addressDTO.getNeighborhood());
            if(addressDTO.getAddressDesc() != null || !addressDTO.getAddressDesc().equals("")) address.setAddressDesc(addressDTO.getAddressDesc());

            return addressRepository.save(address);
        }
        return null;
    }
}
