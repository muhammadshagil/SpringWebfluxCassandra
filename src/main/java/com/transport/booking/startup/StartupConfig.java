package com.transport.booking.startup;

import com.transport.booking.model.Container;
import com.transport.booking.model.ContainerType;
import com.transport.booking.service.ContainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class StartupConfig {

    private final ContainerService containerService;

    @PostConstruct
    public void initData() {
        initCounter();
        setupSampleData();

    }

    private void initCounter() {
        //TODO get next Id from Data base
    }

    private void setupSampleData() {
        List<Container> containerList = new ArrayList<>();
        containerList.add(Container.builder().id(1).quantity(20).containerSize(20).containerType(ContainerType.DRY).origin("Southampton"
        ).destination("Singapore").build());
        containerList.add(Container.builder().id(1).quantity(20).containerSize(40).containerType(ContainerType.DRY).origin("Southampton"
        ).destination("Singapore").build());
        containerList.add(Container.builder().id(3).quantity(30).containerSize(40).containerType(ContainerType.DRY).origin("London"
        ).destination("Delhi").build());

        containerService.saveAll(Flux.fromIterable(containerList)).subscribe();
    }
}
