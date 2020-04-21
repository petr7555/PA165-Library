package cz.muni.fi.pa165.library.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Petr Janik 485122
 * @since 09.03.2020
 * <p>
 * An abstract controller to prepend all api endpoints with "/pa165/rest".
 * All other controllers MUST implement this.
 */
@RequestMapping("/pa165/rest")
public abstract class AbstractController {
}
