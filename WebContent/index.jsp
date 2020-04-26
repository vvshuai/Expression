<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>四则运算生成系统</title>
    <style>
        .big {
            position: absolute;
            left: 50%;
            margin-left: -100px;
            top: 50%;
            margin-top: -200px;
            color: white;
        }
        :root {
            --blur: 2;
        }

        * {
            box-sizing: border-box;
        }

        html,
        body {
            font-family: 'Arial', sans-serif;
            background: #111;
        }

        canvas {
            position: fixed;
            height: 100vh;
            width: 100vw;
            -webkit-filter: blur(calc(var(--blur) * 1px));
            filter: blur(calc(var(--blur) * 1px));
        }

        input {
            cursor: pointer;
        }

        label {
            margin-bottom: 30px;
        }

            label:last-of-type {
                margin-bottom: 0;
            }

        .menu {
            position: absolute;
            top: 0;
            left: 0;
            color: #fafafa;
            background: rgba(0,0,0,0.15);
            display: flex;
            flex-direction: column;
            padding: 30px;
            -webkit-transform: translate(-100%, 0);
            transform: translate(-100%, 0);
            transition: -webkit-transform 0.25s ease-out;
            transition: transform 0.25s ease-out;
            transition: transform 0.25s ease-out, -webkit-transform 0.25s ease-out;
        }

        .menu--open {
            -webkit-transform: translate(0, 0);
            transform: translate(0, 0);
        }

        .icon {
            height: 60%;
            width: 60%;
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

        button {
            height: 44px;
            width: 44px;
            background: 0;
            cursor: pointer;
            border: 0;
            background: rgba(0,0,0,0.15);
            padding: 0;
            margin: 0;
            position: absolute;
            left: 100%;
            top: 0;
        }

            button span {
                width: 100%;
                height: 20%;
                border-radius: 4px;
                background: #fff;
                display: block;
                position: absolute;
                top: 0;
                transition: -webkit-transform 0.25s ease-out;
                transition: transform 0.25s ease-out;
                transition: transform 0.25s ease-out, -webkit-transform 0.25s ease-out;
            }

                button span:nth-child(1) {
                    -webkit-transform-origin: top left;
                    transform-origin: top left;
                }

                button span:nth-child(2) {
                    top: 40%;
                }

                button span:nth-child(3) {
                    -webkit-transform-origin: top left;
                    transform-origin: top left;
                    top: 80%;
                }

        .menu--open span:nth-child(1) {
            -webkit-transform: translate(5px, 3px) rotate(45deg);
            transform: translate(5px, 3px) rotate(45deg);
        }

        .menu--open span:nth-child(2) {
            -webkit-transform: scaleX(0);
            transform: scaleX(0);
        }

        .menu--open span:nth-child(3) {
            -webkit-transform: translate(2px, 0) rotate(-45deg);
            transform: translate(2px, 0) rotate(-45deg);
        }
    </style>
</head>

<body>
    <canvas></canvas>
    <div class="menu">
        <button>
            <div class="icon"><span></span><span></span><span></span></div>
        </button>
        <label>
            Amount
            <input type="range" min="1" max="500" step="1" value="100" id="AMOUNT">
        </label>
        <label>
            Upper Velocity Bounds
            <input type="range" min="1" max="50" step="1" value="20" id="UPPER_LIMIT">
        </label>
        <label>
            Lower Velocity Bounds
            <input type="range" min="1" max="50" step="1" value="1" id="LOWER_LIMIT">
        </label>
        <label>
            Blur
            <input type="range" min="0" max="10" step="1" value="2" id="BLUR">
        </label>
    </div>
    <div class="big">
        <h1>准备答题</h1>
        <!-- 输入出题数和每行的题目数，将其封存于form表单中，按钮点击判定为true时向指定jsp文件提交表单 -->
        <form action="hello" method="post" onsubmit="return Go()">
            确认出题数:<input type="text" style="width:100px" id="num" name="num"><br>
            最大数:<input type="text" style="width:100px" id="num1" name="num1"><br>
            <h4>运算符号选择:</h4>
            <span><input style="vertical-align:middle" type="checkbox" name="use" value="add" /><label style="vertical-align:middle">加法</label></span>
            <span><input style="vertical-align:middle" type="checkbox" name="use" value="sub"/><label style="vertical-align:middle">减法</label></span>
            <span><input style="vertical-align:middle" type="checkbox" name="use" value="mul"/><label style="vertical-align:middle">乘法</label></span>
            <span><input style="vertical-align:middle" type="checkbox" name="use" value="div"/><label style="vertical-align:middle">除法</label></span>
            <span><input style="vertical-align:middle" type="checkbox" name="way" value="cur"/><label style="vertical-align:middle">括号</label></span>
            <span><input style="vertical-align:middle" type="checkbox" name="way" value="dou"/><label style="vertical-align:middle">小数</label></span>
            <h4>保存方式:</h4>
            <span><input style="vertical-align:middle" type="checkbox" name="way" value="web1"/><label style="vertical-align:middle">生成到网页</label></span>
            <span><input style="vertical-align:middle" type="checkbox" name="way" value="file1"/><label style="vertical-align:middle">生成到文件</label></span>
            <br>
            <input type="submit" value="提交">
        </form>
    </div>
    <!-- 判断输入格式是否正确 -->
    <script type="text/javascript">
    function Go() {
        //验证数字的正则表达式
        var x = "^[0-9]*$";
        //抓取文本框中输入的字符串
        var a = document.getElementById("num").value;
        var b = document.getElementById("numh").value;
        //判断
        if (!a.match(x)) {
            alert("请输入正整数");
            return false;
        } else if (!b.match(x)) {
            alert("请输入正整数");
            return false;
        }
    }
    </script>
    <script>
    var canvas = document.querySelector('canvas');
    var context = canvas.getContext('2d');
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    requestAnimationFrame = requestAnimationFrame || webkitRequestAnimationFrame;

    var menu = document.querySelector('.menu');
    var modify = function modify(e) {
        OPTIONS[e.target.id] = parseInt(e.target.value, 10);
        if (e.target.id === 'AMOUNT') {
            context.clearRect(0, 0, canvas.width, canvas.height);
            particles = genParticles();
        }
        if (e.target.id === 'BLUR') {
            document.documentElement.style.setProperty('--blur', parseInt(e.target.value, 10));
        }
    };
    menu.addEventListener('change', modify);

    var button = document.querySelector('button');
    var handleClick = function handleClick(e) {
        return menu.classList.toggle('menu--open');
    };
    button.addEventListener('click', handleClick);

    var OPTIONS = {
        AMOUNT: 100,
        UPPER_LIMIT: 20,
        LOWER_LIMIT: 1
    };

    var UPPER_SIZE = 10;
    var LOWER_SIZE = 4;

    var doIt = function doIt() {
        return Math.random() > 0.5;
    };
    var update = function update(p) {
        return doIt() ? Math.max(OPTIONS.LOWER_LIMIT, p - 1) : Math.min(p + 1, OPTIONS.UPPER_LIMIT);
    };
    var reset = function reset(p) {
        p.x = p.startX;
        p.y = p.startY;
    };
    var floored = function floored(r) {
        return Math.floor(Math.random() * r);
    };
    var genParticles = function genParticles() {
        return new Array(OPTIONS.AMOUNT).fill().map(function(p) {
            var size = floored(UPPER_SIZE) + LOWER_SIZE;
            var c = document.createElement('canvas');
            var ctx = c.getContext('2d');
            var r = Math.PI / 180 * floored(360);
            var color = 'rgba(255,' + (100 + Math.floor(Math.random() * 70)) + ', 0, ' + Math.random() + ')';
            var xDelayed = doIt();
            var startX = xDelayed ? -(size + floored(canvas.width)) : floored(canvas.width * 0.25);
            var startY = xDelayed ? size + floored(canvas.height * 0.25) + Math.floor(canvas.height * 0.75) : canvas.height + size + floored(canvas.height);
            c.height = size;
            c.width = size;
            context.globalCompositeOperation = 'multiply';
            // ctx.filter = `blur(${Math.random() * size}px)`
            ctx.translate(size / 2, size / 2);
            ctx.rotate(r);
            ctx.translate(-(size / 2), -(size / 2));
            ctx.fillStyle = color;
            ctx.fillRect(0, 0, size, size);
            return {
                x: startX,
                y: startY,
                startY: startY,
                startX: startX,
                c: c,
                r: r,
                vx: floored(OPTIONS.UPPER_LIMIT / 4),
                vy: floored(OPTIONS.UPPER_LIMIT / 4),
                size: size
            };
        });
    };

    var particles = genParticles();
    var FRAME_COUNT = 0;

    var draw = function draw() {
        if (canvas.width !== window.innerWidth || canvas.height !== window.innerHeight) {
            canvas.width = window.innerWidth;
            canvas.height = window.innerHeight;
            particles = genParticles();
        }
        // context.restore()
        var _iteratorNormalCompletion = true;
        var _didIteratorError = false;
        var _iteratorError = undefined;

        try {
            for (var _iterator = particles[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
                var particle = _step.value;

                context.clearRect(particle.x, particle.y, particle.size, particle.size);
                FRAME_COUNT++;
                if (particle.y < canvas.height || particle.startX < 0) particle.x += particle.vx;
                if (particle.x > 0 || particle.startY > canvas.height) particle.y -= particle.vy;
                if (FRAME_COUNT % 11 === 0 && doIt()) particle.vx = update(particle.vx);
                if (FRAME_COUNT % 13 === 0 && doIt()) particle.vy = update(particle.vy);
                context.drawImage(particle.c, particle.x, particle.y);
                if (particle.x > canvas.width || particle.y < -particle.size) reset(particle);
            }
        } catch (err) {
            _didIteratorError = true;
            _iteratorError = err;
        } finally {
            try {
                if (!_iteratorNormalCompletion && _iterator.return) {
                    _iterator.return();
                }
            } finally {
                if (_didIteratorError) {
                    throw _iteratorError;
                }
            }
        }

        requestAnimationFrame(draw);
    };
    requestAnimationFrame(draw);
    </script>
</body>

</html>