package usecases.homepage


// language=CSS
val bodyCss = """
    body, html {
      font-family: "Open Sans", sans-serif;
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAIAAAAmkwkpAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAACZJREFUeNpi/PTpEwMY8PLyMsFZnz9/ZoKzgAzG////Q1hAABBgAFeiDto+NxIZAAAAAElFTkSuQmCC") repeat;
    }
    
    /*! normalize.css v8.0.1 | MIT License | github.com/necolas/normalize.css */

    /* Document
       ========================================================================== */

    /**
     * 1. Correct the line height in all browsers.
     * 2. Prevent adjustments of font size after orientation changes in iOS.
     */

    html {
      line-height: 1.15; /* 1 */
      -webkit-text-size-adjust: 100%; /* 2 */
    }

    /* Sections
       ========================================================================== */

    /**
     * Remove the margin in all browsers.
     */

    body {
      margin: 0;
    }

    /**
     * Render the `main` element consistently in IE.
     */

    main {
      display: block;
    }

    /**
     * Correct the font size and margin on `h1` elements within `section` and
     * `article` contexts in Chrome, Firefox, and Safari.
     */

    h1 {
      font-size: 2em;
      margin: 0.67em 0;
    }

    /* Grouping content
       ========================================================================== */

    /**
     * 1. Add the correct box sizing in Firefox.
     * 2. Show the overflow in Edge and IE.
     */

    hr {
      box-sizing: content-box; /* 1 */
      height: 0; /* 1 */
      overflow: visible; /* 2 */
    }

    /**
     * 1. Correct the inheritance and scaling of font size in all browsers.
     * 2. Correct the odd `em` font sizing in all browsers.
     */

    pre {
      font-family: monospace, monospace; /* 1 */
      font-size: 1em; /* 2 */
    }

    /* Text-level semantics
       ========================================================================== */

    /**
     * Remove the gray background on active links in IE 10.
     */

    a {
      background-color: transparent;
    }

    /**
     * 1. Remove the bottom border in Chrome 57-
     * 2. Add the correct text decoration in Chrome, Edge, IE, Opera, and Safari.
     */

    abbr[title] {
      border-bottom: none; /* 1 */
      text-decoration: underline; /* 2 */
      text-decoration: underline dotted; /* 2 */
    }

    /**
     * Add the correct font weight in Chrome, Edge, and Safari.
     */

    b,
    strong {
      font-weight: bolder;
    }

    /**
     * 1. Correct the inheritance and scaling of font size in all browsers.
     * 2. Correct the odd `em` font sizing in all browsers.
     */

    code,
    kbd,
    samp {
      font-family: monospace, monospace; /* 1 */
      font-size: 1em; /* 2 */
    }

    /**
     * Add the correct font size in all browsers.
     */

    small {
      font-size: 80%;
    }

    /**
     * Prevent `sub` and `sup` elements from affecting the line height in
     * all browsers.
     */

    sub,
    sup {
      font-size: 75%;
      line-height: 0;
      position: relative;
      vertical-align: baseline;
    }

    sub {
      bottom: -0.25em;
    }

    sup {
      top: -0.5em;
    }

    /* Embedded content
       ========================================================================== */

    /**
     * Remove the border on images inside links in IE 10.
     */

    img {
      border-style: none;
    }

    /* Forms
       ========================================================================== */

    /**
     * 1. Change the font styles in all browsers.
     * 2. Remove the margin in Firefox and Safari.
     */

    button,
    input,
    optgroup,
    select,
    textarea {
      font-family: inherit; /* 1 */
      font-size: 100%; /* 1 */
      line-height: 1.15; /* 1 */
      margin: 0; /* 2 */
    }

    /**
     * Show the overflow in IE.
     * 1. Show the overflow in Edge.
     */

    button,
    input { /* 1 */
      overflow: visible;
    }

    /**
     * Remove the inheritance of text transform in Edge, Firefox, and IE.
     * 1. Remove the inheritance of text transform in Firefox.
     */

    button,
    select { /* 1 */
      text-transform: none;
    }

    /**
     * Correct the inability to style clickable types in iOS and Safari.
     */

    button,
    [type="button"],
    [type="reset"],
    [type="submit"] {
      -webkit-appearance: button;
    }

    /**
     * Remove the inner border and padding in Firefox.
     */

    button::-moz-focus-inner,
    [type="button"]::-moz-focus-inner,
    [type="reset"]::-moz-focus-inner,
    [type="submit"]::-moz-focus-inner {
      border-style: none;
      padding: 0;
    }

    /**
     * Restore the focus styles unset by the previous rule.
     */

    button:-moz-focusring,
    [type="button"]:-moz-focusring,
    [type="reset"]:-moz-focusring,
    [type="submit"]:-moz-focusring {
      outline: 1px dotted ButtonText;
    }

    /**
     * Correct the padding in Firefox.
     */

    fieldset {
      padding: 0.35em 0.75em 0.625em;
    }

    /**
     * 1. Correct the text wrapping in Edge and IE.
     * 2. Correct the color inheritance from `fieldset` elements in IE.
     * 3. Remove the padding so developers are not caught out when they zero out
     *    `fieldset` elements in all browsers.
     */

    legend {
      box-sizing: border-box; /* 1 */
      color: inherit; /* 2 */
      display: table; /* 1 */
      max-width: 100%; /* 1 */
      padding: 0; /* 3 */
      white-space: normal; /* 1 */
    }

    /**
     * Add the correct vertical alignment in Chrome, Firefox, and Opera.
     */

    progress {
      vertical-align: baseline;
    }

    /**
     * Remove the default vertical scrollbar in IE 10+.
     */

    textarea {
      overflow: auto;
    }

    /**
     * 1. Add the correct box sizing in IE 10.
     * 2. Remove the padding in IE 10.
     */

    [type="checkbox"],
    [type="radio"] {
      box-sizing: border-box; /* 1 */
      padding: 0; /* 2 */
    }

    /**
     * Correct the cursor style of increment and decrement buttons in Chrome.
     */

    [type="number"]::-webkit-inner-spin-button,
    [type="number"]::-webkit-outer-spin-button {
      height: auto;
    }

    /**
     * 1. Correct the odd appearance in Chrome and Safari.
     * 2. Correct the outline style in Safari.
     */

    [type="search"] {
      -webkit-appearance: textfield; /* 1 */
      outline-offset: -2px; /* 2 */
    }

    /**
     * Remove the inner padding in Chrome and Safari on macOS.
     */

    [type="search"]::-webkit-search-decoration {
      -webkit-appearance: none;
    }

    /**
     * 1. Correct the inability to style clickable types in iOS and Safari.
     * 2. Change font properties to `inherit` in Safari.
     */

    ::-webkit-file-upload-button {
      -webkit-appearance: button; /* 1 */
      font: inherit; /* 2 */
    }

    /* Interactive
       ========================================================================== */

    /*
     * Add the correct display in Edge, IE 10+, and Firefox.
     */

    details {
      display: block;
    }

    /*
     * Add the correct display in all browsers.
     */

    summary {
      display: list-item;
    }

    /* Misc
       ========================================================================== */

    /**
     * Add the correct display in IE 10+.
     */

    template {
      display: none;
    }

    /**
     * Add the correct display in IE 10.
     */

    [hidden] {
      display: none;
    }

""".trimIndent()

// language=CSS
val commonCss = """
    :root {
      --bg-light: #f0f1f5;
      --bg-light-blue: #eaeef4;
      --light-blue: #C7D4E5;
      --blue: #6D8DBA;
    
      --dark-blue: #0057b8;
      --accent: #ffd700;
      --gray: #AEB1B7;
    
      --tb: 1024px;
      --mb: 768px;
    }
""".trimIndent()

// Compile LESS manually
// https://lesscss.org/less-preview/

// language=LESS
private val navigationStylesLess = """
    .navigation {
      &_bar {
        background-color: var(--dark-blue);

        & > * {
          box-sizing: border-box;
        }

        &_wrapper {
          display: flex;
          flex-direction: row;
          flex-wrap: wrap;
          max-width: 1024px;
          margin: auto;
          padding: 20px;

          & .separator {
            font-size: 24px;
            color: var(--accent);
            margin: 10px;
            cursor: default;
            user-select: none;
          }

          & a.link {
            font-size: 24px;
            color: var(--accent);
            text-decoration: none;
            margin: 10px;

            &.active {
              text-decoration: underline;
              text-decoration-thickness: 2.5px;
            }
          }
        }
      }
    }
""".trimIndent()

// language=CSS
val navigationCss = """
    .navigation_bar {
      background-color: var(--dark-blue);
    }
    .navigation_bar > * {
      box-sizing: border-box;
    }
    .navigation_bar_wrapper {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      max-width: 1024px;
      margin: auto;
      padding: 20px;
    }
    .navigation_bar_wrapper .separator {
      font-size: 24px;
      color: var(--accent);
      margin: 10px;
      cursor: default;
      user-select: none;
    }
    .navigation_bar_wrapper a.link {
      font-size: 24px;
      color: var(--accent);
      text-decoration: none;
      margin: 10px;
    }
    .navigation_bar_wrapper a.link.active {
      text-decoration: underline;
      text-decoration-thickness: 2.5px;
    }
""".trimIndent()

// language=LESS
private val headerLess = """
    .head {
      display: block;

      &_wrapper {
        display: block;
        max-width: 1024px;
        margin: auto;
      }

      &_logo {
        width: auto;
        max-width: 100%;
        height: auto;
        min-height: 140px;
        max-height: 140px;
        display: block;
        margin: 0 auto;
        padding: 20px 0;
        opacity: 0.9;

        &:hover {
          opacity: 1;
        }
      }

      @media screen and (max-width: 480px) {
        &_logo {
          max-width: 200px;
          min-height: 50px;
          max-height: 50px;
        }
      }
    }
""".trimIndent()

// language=CSS
val headerCss = """
    .head {
      display: block;
    }
    .head_wrapper {
      display: block;
      max-width: 1024px;
      margin: auto;
    }
    .head_logo {
      width: auto;
      max-width: 100%;
      height: auto;
      min-height: 140px;
      max-height: 140px;
      display: block;
      margin: 0 auto;
      padding: 20px 0;
      opacity: 0.9;
    }
    .head_logo:hover {
      opacity: 1;
    }
    @media screen and (max-width: 480px) {
      .head_logo {
        max-width: 200px;
        min-height: 50px;
        max-height: 50px;
      }
    }

""".trimIndent()

// language=LESS
private val barLess = """
    .bar {
      background-color: rgba(242, 242, 242, 0.7);

      & > * {
        box-sizing: border-box;
      }

      &_wrapper {
        display: block;
        max-width: 1024px;
        margin: auto;
        padding: 20px;
      }
    }
""".trimIndent()

// language=CSS
val barCss = """
    .bar {
      background-color: rgba(242, 242, 242, 0.7);
    }
    
    .bar > * {
      box-sizing: border-box;
    }
    
    .bar_wrapper {
      display: block;
      max-width: 1024px;
      margin: auto;
      padding: 20px;
    }
""".trimIndent()

// language=LESS
private val searchLess = """
    .search {
      display: block;
      background: rgba(250, 250, 250, 0.7);

      * {
        box-sizing: border-box;
      }

      &_wrapper {
        display: block;
        max-width: 1024px;
        margin: auto;
        padding: 20px;
      }

      &_field {
        border: 2px solid var(--dark-blue);
        display: block;
        width: 100%;
        background: #fff url('/search/search.svg') 98.8% 13px no-repeat;
        background-size: 26px 26px;
        padding: 10px 40px 10px 15px;
        outline-color: var(--accent);
        color: var(--dark-blue);
        font-size: 24px;
        font-family: 'Open Sans', sans-serif;
        font-weight: 300;
        &:focus {
          border-color: var(--accent);
          outline: none;
        }
        &::-webkit-input-placeholder {
          color: #C7D4E5;
        }
      }
      @media screen and (max-width: 480px) {
        &_wrapper {
          padding: 20px 10px;
        }

        &_field {
          border-width: 1px;
          background-position: 98.8% 6px;
          background-size: 20px 20px;
          padding: 5px 32px 5px 10px;
          font-size: 16px;
        }
      }
    }
""".trimIndent()

// language=CSS
val searchCss = """
    .search {
      display: block;
      background: rgba(250, 250, 250, 0.7);
    }
    .search * {
      box-sizing: border-box;
    }
    .search_wrapper {
      display: block;
      max-width: 1024px;
      margin: auto;
      padding: 20px;
    }
    .search_field {
      border: 2px solid var(--dark-blue);
      display: block;
      width: 100%;
      background: #fff url('/search/search.svg') 98.8% 13px no-repeat;
      background-size: 26px 26px;
      padding: 10px 40px 10px 15px;
      outline-color: var(--accent);
      color: var(--dark-blue);
      font-size: 24px;
      font-family: 'Open Sans', sans-serif;
      font-weight: 300;
    }
    .search_field:focus {
      border-color: var(--accent);
      outline: none;
    }
    .search_field::-webkit-input-placeholder {
      color: #C7D4E5;
    }
    @media screen and (max-width: 480px) {
      .search_wrapper {
        padding: 20px 10px;
      }
      .search_field {
        border-width: 1px;
        background-position: 98.8% 6px;
        background-size: 20px 20px;
        padding: 5px 32px 5px 10px;
        font-size: 16px;
      }
    }
""".trimIndent()

// language=LESS
private val kotlinVersionLess = """
    .kotlin_version {
      font-family: monospace;
      font-size: 20px;
      cursor: pointer;
      padding: 10px;
      margin: 10px;
      display: inline-block;
      background-color: rgba(250, 250, 250, 0.7);

      &:hover {
        background-color: rgba(255, 255, 255, 0.7);
      }
    }
""".trimIndent()

// language=CSS
val kotlinVersionCss = """
    .kotlin_version {
      font-family: monospace;
      font-size: 20px;
      cursor: text;
      padding: 10px;
      margin: 10px;
      display: inline-block;
      background-color: rgba(250, 250, 250, 0.7);
    }
    .kotlin_version:hover {
      background-color: rgba(255, 255, 255, 0.7);
    }
""".trimIndent()


// language=LESS
private val categoryLess = """
    .category {
      &:nth-child(2n) {
        background: white;
      }
      &:nth-child(2n+1) {
        background: rgba(255, 255, 255, .6);
      }

      display: block;
      border-bottom: 1px solid rgba(204, 204, 204, .2);
      border-top: 1px solid white;

      &_title {
        position: relative;
        overflow: hidden;

        color: var(--dark-blue);
        letter-spacing: 4px;
        text-align: center;
        text-transform: uppercase;
        font-size: 30px;
      }
      &_wrapper {
        display: block;
        max-width: 1024px;
        margin: auto;
        padding: 30px 20px;
        overflow: hidden;

        &_lists {

        }
      }
    }
""".trimIndent()

// language=CSS
val categoryCss = """
    .category {
      display: block;
      border-bottom: 1px solid rgba(204, 204, 204, 0.2);
      border-top: 1px solid white;
    }
    .category:nth-child(2n) {
      background: white;
    }
    .category:nth-child(2n+1) {
      background: rgba(255, 255, 255, 0.6);
    }
    .category_title {
      position: relative;
      overflow: hidden;
      color: var(--dark-blue);
      letter-spacing: 4px;
      text-align: center;
      text-transform: uppercase;
      font-size: 30px;
    }
    .category_wrapper {
      display: block;
      max-width: 1024px;
      margin: auto;
      padding: 30px 20px;
      overflow: hidden;
    }
""".trimIndent()

// language=LESS
private val listLess = """
    .list {
      display: inline-block;
      width: (100% / 3);
      padding: 10px 20px;
      vertical-align: top;
      box-sizing: border-box;

      font-size: 0;
      @media screen and (max-width: 1024px) {
        width: (100% / 2);
      }
      @media screen and (max-width: 768px) {
        width: (100%);
      }

      &_title a {
        font-size: 24px;
        color: darken(var(--accent), 5%);
        text-decoration: none;
      }
      &_list {
        padding: 0;
        list-style: none;
        font-size: 16px;
      }
    }
""".trimIndent()

// language=CSS
val listCss = """
    .list {
      display: inline-block;
      width: 33.33333333%;
      padding: 10px 20px;
      vertical-align: top;
      box-sizing: border-box;
      font-size: 0;
    }
    @media screen and (max-width: 1024px) {
      .list {
        width: 50%;
      }
    }
    @media screen and (max-width: 768px) {
      .list {
        width: 100%;
      }
    }
    .list_title a {
      font-size: 24px;
      color: #e6c200;
      text-decoration: none;
    }
    .list_list {
      padding: 0;
      list-style: none;
      font-size: 16px;
    }
""".trimIndent()

// language=LESS
private val listItemLess = """
    .listitem {
      padding: 10px 5px;

      &_archived,
      &_unsupported {
        opacity: 0.4;
      }

      &_link {
        display: block;

        text-decoration: none;
        color: darken(#6D8DBA, 15%);

        &:hover {
          color: darken(#6D8DBA, 30%);
        }
      }

      &_description {
        margin: 0;

        & p {
          margin: 0;
        }

        & a {
          color: var(--gray);

          &:hover {
            color: darken(#AEB1B7, 15%);
          }
        }

        font-size: 14px;
        color: var(--gray);

        &:empty {
          display: none;
        }
      }

      &_star {
        float: right;

        + .listitem_link {
          padding-right: 60px;
        }

        &_icon {
          display: inline-block;
          vertical-align: top;
          width: 20px;
          height: 20px;

          background-size: 20px 20px;
          color: var(--light-blue);
        }

        &_count {
          display: inline-block;
          vertical-align: top;

          color: darken(#6D8DBA, 15%);
          font-size: 14px;
          line-height: 20px;
        }
      }
    }
""".trimIndent()

// language=CSS
val listItemCss = """
    .listitem {
      padding: 10px 5px;
    }
    .listitem_archived,
    .listitem_unsupported {
      opacity: 0.4;
    }
    .listitem_link {
      display: block;
      text-decoration: none;
      color: #466794;
    }
    .listitem_link:hover {
      color: #2e4360;
    }
    .listitem_description {
      margin: 0;
      font-size: 14px;
      color: var(--gray);
    }
    .listitem_description p {
      margin: 0;
    }
    .listitem_description a {
      color: var(--gray);
    }
    .listitem_description a:hover {
      color: #858a93;
    }
    .listitem_description:empty {
      display: none;
    }
    .listitem_star {
      float: right;
    }
    .listitem_star + .listitem_link {
      padding-right: 60px;
    }
    .listitem_star_icon {
      display: inline-block;
      vertical-align: top;
      width: 20px;
      height: 20px;
      background-size: 20px 20px;
      color: var(--light-blue);
    }
    .listitem_star_count {
      display: inline-block;
      vertical-align: top;
      color: #466794;
      font-size: 14px;
      line-height: 20px;
    }
""".trimIndent()
