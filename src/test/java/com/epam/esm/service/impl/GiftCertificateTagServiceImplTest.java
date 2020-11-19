package com.epam.esm.service.impl;

import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.repository.tagcertificate.GiftCertificateRepositoryImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GiftCertificateTagServiceImplTest {
    @InjectMocks
    private GIftCertificateTagServiceImpl service;
    @Mock
    private GiftCertificateRepositoryImpl repository;

    @Test
    public void shouldAddRowTrue() {
        long giftId = 1;
        long tagId = 1;
        long expectedResult = 1;
        Mockito.when(repository.add(Mockito.anyLong(), Mockito.anyLong())).thenReturn(expectedResult);
        long actualResult = service.add(giftId, tagId);
        Mockito.verify(repository).add(Mockito.eq(giftId), Mockito.eq(tagId));
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldRemoveRowTrue() {
        long giftId = 1;
        long tagId = 1;
        long expectedResult = 1;
        Mockito.when(repository.remove(Mockito.anyLong(), Mockito.anyLong())).thenReturn(expectedResult);
        long actualResult = service.remove(giftId, tagId);
        Mockito.verify(repository).remove(Mockito.eq(giftId), Mockito.eq(tagId));
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test()
    void shouldNotAddRowException() throws EntityNotAddedException {
        Throwable expectedThrown = new EntityNotAddedException("Not added");
        long giftId = 1;
        long tagId = 1;
        Mockito.when(repository.add(Mockito.anyLong(), Mockito.anyLong())).thenThrow(expectedThrown);
        Assertions.assertThrows(EntityNotAddedException.class, () -> {
            service.add(giftId, tagId);
        });
        Mockito.verify(repository).add(Mockito.eq(giftId), Mockito.eq(tagId));
    }
    @Test()
    void shouldNotRemovedRowException() throws EntityNotDeletedException {
        long giftId = 1;
        long tagId = 1;
        Throwable expectedThrown = new EntityNotDeletedException("Not removed", giftId);
        Mockito.when(repository.remove(Mockito.anyLong(), Mockito.anyLong())).thenThrow(expectedThrown);
        Assertions.assertThrows(EntityNotDeletedException.class, () -> {
            service.remove(giftId, tagId);
        });
        Mockito.verify(repository).remove(Mockito.eq(giftId), Mockito.eq(tagId));
    }
}