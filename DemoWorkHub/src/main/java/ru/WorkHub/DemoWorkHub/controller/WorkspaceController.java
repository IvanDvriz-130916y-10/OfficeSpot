package ru.WorkHub.DemoWorkHub.controller; // Пакет соответствует проекту WorkHub

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.WorkHub.DemoWorkHub.entity.Workspace; // Используем сущность Workspace
import ru.WorkHub.DemoWorkHub.repository.WorkspaceRepository; // Используем репозиторий WorkspaceRepository

import java.util.Optional;

@Slf4j
@Controller
public class WorkspaceController { // Контроллер для управления Workspace

    @Autowired
    private WorkspaceRepository workspaceRepository; // Инъекция зависимости репозитория

    @GetMapping("/list")
    public ModelAndView getAllWorkspaces() {
        log.info("/list -> connection"); // Логирование для отладки
        ModelAndView mav = new ModelAndView("list-workspaces"); // Возвращаем представление списка рабочих пространств
        mav.addObject("workspaces", workspaceRepository.findAll());
        return mav;
    }

    @GetMapping("/addWorkspaceForm")
    public ModelAndView addWorkspaceForm() {
        ModelAndView mav = new ModelAndView("add-workspace-form"); // Форма добавления рабочего пространства
        Workspace workspace = new Workspace();
        mav.addObject("workspace", workspace);
        return mav;
    }

    @PostMapping("/saveWorkspace")
    public String saveWorkspace(@ModelAttribute Workspace workspace) {
        workspaceRepository.save(workspace); // Сохраняем рабочее пространство
        return "redirect:/list"; // Перенаправление на список рабочих пространств
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long workspaceId) {
        ModelAndView mav = new ModelAndView("add-workspace-form"); // Используем ту же форму для обновления
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceId);
        Workspace workspace = new Workspace();
        if (optionalWorkspace.isPresent()) {
            workspace = optionalWorkspace.get();
        }
        mav.addObject("workspace", workspace);
        return mav;
    }

    @GetMapping("/deleteWorkspace")
    public String deleteWorkspace(@RequestParam Long workspaceId) {
        workspaceRepository.deleteById(workspaceId); // Удаляем рабочее пространство по ID
        return "redirect:/list";
    }
}