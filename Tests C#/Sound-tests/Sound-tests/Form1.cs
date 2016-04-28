using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Sound_tests
{
    public partial class Form1 : Form
    {

        string soundsFolder = "";

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            //Set folder path
            soundsFolder = Environment.CurrentDirectory;
            soundsFolder = soundsFolder.Replace(@"\bin\Debug", @"\sounds");
            soundsFolder = soundsFolder.Replace(@"\bin\Release", @"\sounds");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            label1.Text = "Do Clicked";

            WMPLib.WindowsMediaPlayer wplayer = new WMPLib.WindowsMediaPlayer();
            wplayer.URL = soundsFolder + @"\c.wav";
            wplayer.controls.play();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            label1.Text = "Mi Clicked";

            WMPLib.WindowsMediaPlayer wplayer = new WMPLib.WindowsMediaPlayer();
            wplayer.URL = soundsFolder + @"\e.wav";
            wplayer.controls.play();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            label1.Text = "Sol Clicked";

            WMPLib.WindowsMediaPlayer wplayer = new WMPLib.WindowsMediaPlayer();
            wplayer.URL = soundsFolder + @"\g.wav";
            wplayer.controls.play();
        }
    }
}
