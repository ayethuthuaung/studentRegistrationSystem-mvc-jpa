
  (function($) {
    'use strict';
    // Function to handle sidebar initialization
    function initializeSidebar() {
      var body = $('body');
      var sidebarIconOnly = body.hasClass("sidebar-icon-only");
      var sidebarFixed = body.hasClass("sidebar-fixed");

      // Check if the sidebar is in icon-only mode
      if (!sidebarIconOnly) {
        // Iterate over each nav item in the sidebar
        $('.sidebar .nav-item').each(function() {
          var $menuItem = $(this);
          // Check if the nav item has a submenu
          if ($menuItem.find('.collapse').length > 0) {
            // Check if the nav item is currently active
            var isActive = $menuItem.hasClass('active');
            // Show/hide the submenu based on the active state
            $menuItem.find('.collapse').collapse(isActive ? 'show' : 'hide');
          }
        });
      }
    }

    // Call the initializeSidebar function when the document is ready
    $(document).ready(function() {
      initializeSidebar();
    });

    //Open submenu on hover in compact sidebar mode and horizontal menu mode
    $(document).on('mouseenter mouseleave', '.sidebar .nav-item', function(ev) {
      var body = $('body');
      var sidebarIconOnly = body.hasClass("sidebar-icon-only");
      var sidebarFixed = body.hasClass("sidebar-fixed");
      if (!('ontouchstart' in document.documentElement)) {
        if (sidebarIconOnly) {
          if (sidebarFixed) {
            if (ev.type === 'mouseenter') {
              body.removeClass('sidebar-icon-only');
            }
          } else {
            var $menuItem = $(this);
            if (ev.type === 'mouseenter') {
              $menuItem.addClass('hover-open')
            } else {
              $menuItem.removeClass('hover-open')
            }
          }
        }
      }
    });

    $('.aside-toggler').click(function(){
      $('.chat-list-wrapper').toggleClass('slide')
    });
  })(jQuery);

